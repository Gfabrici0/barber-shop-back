package com.br.barbershop.help;

public class StringUtil {

  public static String FormatCPF(String cpf) {
    return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
  }

  public static String FormatPhoneNumber(String phoneNumber) {
    String digits = phoneNumber.replaceAll("\\D", "");

    if (digits.length() == 10) {
      return String.format("(%s) %s-%s",
          digits.substring(0, 2),
          digits.substring(2, 6),
          digits.substring(6, 10));
    } else if (digits.length() == 11) {
      return String.format("(%s) %s-%s",
          digits.substring(0, 2),
          digits.substring(2, 7),
          digits.substring(7, 11));
    } else {
      return phoneNumber;
    }
  }

  public static String formatZipCode(String zipCode) throws IllegalArgumentException {
    if (zipCode == null || zipCode.length() != 8) {
      throw new IllegalArgumentException("Invalid ZIP code. The ZIP code must contain 8 digits.");
    }

    return zipCode.substring(0, 5) + "-" + zipCode.substring(5);
  }

}
