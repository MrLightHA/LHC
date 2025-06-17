package me.lightha.lhc.api;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface ChatUtils {
    Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    String parse(String message, Map<String, Object> replaceString);

    List<String> parse(List<String> message, Map<String, Object> replaceString);
}
