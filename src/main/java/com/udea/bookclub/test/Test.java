///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package com.udea.bookclub.test;
//
//import com.udea.bookclub.business.IUserBusiness;
//import com.udea.bookclub.business.UserBusiness;
//import com.udea.bookclub.domain.User;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author Jon Leal
// */
//public class Test {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        IUserBusiness userBusiness = new UserBusiness();
//        List<User> users = userBusiness.getUsers();
//        for (User user : users) {
//            System.out.println(user);
//            System.out.println(userBusiness.verifyCredentials(user));
//        }
//
//        User u = new User("joleal", "joleal@live.com", "4321", new Date());
//        System.out.println(userBusiness.verifyCredentials(u));
//    }
//
//}
