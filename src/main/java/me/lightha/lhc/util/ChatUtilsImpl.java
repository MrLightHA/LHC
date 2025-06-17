package me.lightha.lhc.util;

import me.lightha.lhc.api.ChatUtils;
import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ChatUtilsImpl implements ChatUtils {

    @Override
    public String parse(String message, Map<String, Object> replaceString) {
        for(Matcher matcher = pattern.matcher(message); matcher.find(); matcher = pattern.matcher(message)) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            StringBuilder builder = new StringBuilder();
            replaceSharp.chars().forEach((c) -> {
                builder.append("&").append((char)c);
            });
            message = message.replace(hexCode, builder.toString());
        }
        String deParseMessage = ChatColor.translateAlternateColorCodes('&', message).replace("&", "");
        for (Map.Entry<String, Object> entry : replaceString.entrySet()) {
            return deParseMessage.replace(entry.getKey(), parse(String.valueOf(entry.getValue()), new HashMap<>()));
        }
        return deParseMessage;
    }

    @Override
    public List<String> parse(List<String> messages, Map<String, Object> replaceString) {
        return messages.stream()
                .map(line -> parse(line, replaceString))
                .collect(Collectors.toList());
    }
}
