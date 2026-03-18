package com.smartpower.power_monitor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ActivityLog {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String message;

private LocalDateTime timestamp;

public ActivityLog(){}

public Long getId(){
return id;
}

public String getMessage(){
return message;
}

public void setMessage(String message){
this.message = message;
}

public LocalDateTime getTimestamp(){
return timestamp;
}

public void setTimestamp(LocalDateTime timestamp){
this.timestamp = timestamp;
}

}