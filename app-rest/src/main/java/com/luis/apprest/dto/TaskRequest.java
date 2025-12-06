package com.luis.apprest.dto;

import java.util.List;
/*
Un record genera las siguientes cosas:
1. public constructor con todos los argumentos
2. Getters pero NO setters
3. equals & hashCode
4. toString
5. Inmutabilidad ya que todos los campos son private final.
*/
public record TaskRequest(
        String title,
        String description,
        List<String> items,
        String username
) {
}
