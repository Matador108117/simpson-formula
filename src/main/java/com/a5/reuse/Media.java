package com.a5.reuse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
public class Media {
 public static double calculateMedia(List<Double> list){
  if (list == null || list.isEmpty()) {
   return 0.0;
  }
  double media = 0;
  for (double number: list){
   media += number;
  }
  media /= list.size();
    media = new BigDecimal(media).setScale(2, RoundingMode.HALF_UP).doubleValue();

    return media;
 }
}