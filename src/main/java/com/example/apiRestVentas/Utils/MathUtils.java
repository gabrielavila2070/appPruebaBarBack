package com.example.apiRestVentas.Utils;

import org.springframework.stereotype.Component;

@Component
public class MathUtils {

    public double calculatePercentage(double value, double percentage){
        return (value*((percentage/100)+1));
    }
}
