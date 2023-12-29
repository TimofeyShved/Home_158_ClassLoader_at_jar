package com.company;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        // Подключение библиотек и различных классов (JAR)
        // Указываем путь и загружаем его в загрузчик классов
        URL myUrl = new URL("C://Users/Admin/IdeaProjects/Home_158_ClassLoader_at_jar/MyJar.jar");
        URLClassLoader pluginClassLoader = new URLClassLoader(new URL[]{ myUrl });

        // Вытаскиваем класс из загрузчика
        Class myClass = pluginClassLoader.loadClass("MyClassName");
    }
}

// реализуем свою логику в загрузчике (не обязательно)
class MyLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // считыает массив байтов (при желании можно их сдвигать или шифровать, например что бы защитить от декадирования)
        byte[] bytes = new byte[0];
        try {
            bytes= Files.readAllBytes(Paths.get("filename"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> cl = defineClass(name, bytes, 0, bytes.length);
        return cl;
    }
}
