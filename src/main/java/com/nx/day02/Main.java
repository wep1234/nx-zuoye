package com.nx.day02;

public class Main {
    public static void main(String[] args) throws Exception {

        //类加载器加载的目录
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        //自定义加载器
        //SelfClassLoader classLooader = new SelfClassLoader("d:\\nx\\", "samuel");
        //Class<?> dogClazz = classLooader.loadClass("com.naixue.Dog");
        NetworkClassLoader classLooader = new NetworkClassLoader("http://localhost/class/", "samuel");
        Class<?> dogClazz = classLooader.loadClass("com.naixue.Dog");
        Object dogObj = dogClazz.newInstance();
        //SelfClassLoader classLooader1 = new SelfClassLoader("d:\\nx\\", "samuel");
        //Class<?> dogClazz1 = classLooader1.loadClass("com.naixue.Dog");
        NetworkClassLoader classLooader1 = new NetworkClassLoader("http://localhost/class/", "samuel");
        Class<?> dogClazz1 = classLooader1.loadClass("com.naixue.Dog");
        Object dogObj2 = dogClazz1.newInstance();
        System.out.println("dog1:"+dogObj.getClass().getClassLoader());
        System.out.println("dog1:"+dogObj.getClass().getClassLoader().getParent());
        System.out.println("dog1:"+dogObj.getClass().getClassLoader().getParent().getParent());
//        System.out.println(dogObj.getClass().getClassLoader().getParent().getParent().getParent());
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("dog2:"+dogObj2.getClass().getClassLoader());
        System.out.println("dog2:"+dogObj2.getClass().getClassLoader().getParent());
        System.out.println("dog2:"+dogObj2.getClass().getClassLoader().getParent().getParent());




    }
}
