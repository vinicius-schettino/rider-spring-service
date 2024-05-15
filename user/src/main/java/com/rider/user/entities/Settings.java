package com.rider.user.entities;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    //language  portugues/ingles/chines/espanhol
    //app_appearance  dark/light
    //notification  on/off
    //ads  on/off
}

