package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Chat {

    @Id
    private String id;

    private String user1;
    private String user2;
    private String message;

    public Chat() {
    }

    public Chat(String user1, String user2, String message) {
        this.user1 = user1;
        this.user2 = user2;
        this.message = message;
    }

    public String getChat() {
        return "id, '" + getUser1() + "', '" + getUser2() + "', '" + getMessage() + "'";
    }
}


//package com.example.demo;
//
//public class Chat {
//    int id;
//    String user1;
//    String user2;
//    String message;
//
//    Chat(int id, String user1, String user2, String message){
//        this.id = id;
//        this.user1 = user1;
//        this.user2 = user2;
//        this.message = message;
//    }
//    Chat(String user1, String user2, String message){
//        this.user1 = user1;
//        this.user2 = user2;
//        this.message = message;
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
//    public String getUser1() {
//        return user1;
//    }
//
//    public void setUser1(String user1) {
//        this.user1 = user1;
//    }
//
//    public String getUser2() {
//        return user2;
//    }
//
//    public void setUser2(String user2) {
//        this.user2 = user2;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getChat(){
//        String str = "";
//        str+="id, '"+getUser1()+"', '"+getUser2()+"', '"+getMessage()+"'";
//        return str;
//    }
//}
