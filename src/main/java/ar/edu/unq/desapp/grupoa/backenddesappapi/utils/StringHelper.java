package ar.edu.unq.desapp.grupoa.backenddesappapi.utils;

public class StringHelper {
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty() || value.trim().isEmpty();
    }
}
