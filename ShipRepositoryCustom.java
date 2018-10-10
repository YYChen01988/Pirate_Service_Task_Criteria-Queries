package com.example.codeclan.pirateservice.repository.ships;

import com.example.codeclan.pirateservice.models.Pirate;
import com.example.codeclan.pirateservice.models.Ship;

import java.util.List;

public interface ShipRepositoryCustom {

    List<Pirate> getPiratesForShip(Ship ship);

    Ship findShipByName(String name);
}
