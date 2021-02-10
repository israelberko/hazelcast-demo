//package com.k1.demo.domain;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.keyvalue.annotation.KeySpace;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@KeySpace("test-clogs-5")
//public class Clog implements Serializable {
//    @Id
//    private int id;
//    private String name;
//    private String english;
//    private String french;
//    private String spanish;
//
//    public Clog() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEnglish() {
//        return english;
//    }
//
//    public void setEnglish(String english) {
//        this.english = english;
//    }
//
//    public String getFrench() {
//        return french;
//    }
//
//    public void setFrench(String french) {
//        this.french = french;
//    }
//
//    public String getSpanish() {
//        return spanish;
//    }
//
//    public void setSpanish(String spanish) {
//        this.spanish = spanish;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Clog clog = (Clog) o;
//        return id == clog.id &&
//                Objects.equals(english, clog.english) &&
//                Objects.equals(french, clog.french) &&
//                Objects.equals(spanish, clog.spanish);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, english, french, spanish);
//    }
//}
