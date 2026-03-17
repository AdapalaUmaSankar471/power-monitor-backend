// package com.smartpower.power_monitor.model;

// import jakarta.persistence.*;

// @Entity
// public class Device {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;

//     private double powerRating;

//     private boolean status;

//     public Device() {}

//     public Device(String name, double powerRating, boolean status) {
//         this.name = name;
//         this.powerRating = powerRating;
//         this.status = status;
//     }

//     public Long getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public double getPowerRating() {
//         return powerRating;
//     }

//     public boolean isStatus() {
//         return status;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setPowerRating(double powerRating) {
//         this.powerRating = powerRating;
//     }

//     public void setStatus(boolean status) {
//         this.status = status;
//     }
// }


package com.smartpower.power_monitor.model;

import jakarta.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int power;

    private boolean status;

    public Long getId(){ return id; }

    public String getName(){ return name; }

    public void setName(String name){ this.name=name; }

    public int getPower(){ return power; }

    public void setPower(int power){ this.power=power; }

    public boolean isStatus(){ return status; }

    public void setStatus(boolean status){ this.status=status; }
}





