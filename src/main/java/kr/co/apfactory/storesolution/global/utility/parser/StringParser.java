package kr.co.apfactory.storesolution.global.utility.parser;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StringParser {

    // 통화 1000자리 , 파싱
    public String addCommaToNumber(Integer num) {
        if (num == null || num.equals("")) {
            return "";
        } else {
            return String.format("%,d", num);
        }
    }

    // 통화 1000자리 , 파싱
    public String addCommaToNumber(Long num) {
        if (num == null || num.equals("")) {
            return "";
        } else {
            return String.format("%,d", num);
        }
    }

    // 통화 1000자리 , 파싱
    public String addCommaToNumber(BigDecimal num) {
        if (num == null || num.equals("")) {
            return "";
        } else {
            return String.format("%,d", num.longValue());
        }
    }

    public String changeToString(String str) {
        if (str != null && !str.equals("")) {
            str.trim();
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("'", "&#039;");
            str = str.replaceAll("\"", "&quot;");
        }
        return str;
    }

    public String changeToHTML(String str) {
        if (str != null) {
            str = str.replaceAll("&amp;", "&");
            str = str.replaceAll("&lt;", "<");
            str = str.replaceAll("&gt;", ">");
            str = str.replaceAll("&#039;", "'");
            str = str.replaceAll("&quot;", "\"");
        }
        return str;
    }

    public String changeToEnter(String str) {
        if (str != null && !str.equals("")) {
            str = changeToHTML(str);
            str = str.replaceAll("<br>", "\r\n");
        }
        return str;
    }

    public String changeToHTMLForEditor(String str) {
        if (str != null) {
            str = str.replaceAll("&lt;", "<");
            str = str.replaceAll("&gt;", ">");
            str = str.replaceAll("&amp;", "&");
            str = str.replaceAll("&quot;", "\"");
            str = str.replaceAll("\r\n", "<br>");
            str = str.replaceAll("\n", "<br>");
        }
        return str;
    }

    public String maskingString(String target, int maskLength) {
        if (target == null || target.equals("")) {
            return null;
        }

        int size = target.length();
        if (size > 1) {
            target = target.substring(0, size - maskLength);

            for (int i = 0; i < maskLength; i++) {
                target += "*";
            }
        } else {
            target = "*";
        }

        return target;
    }

    public String addDashToMobileNumber(String phoneNo) {
        String buffer = phoneNo;
        if (phoneNo != null) {
            if (phoneNo.length() == 10) {
                buffer = phoneNo.substring(0, 3) + "-" + phoneNo.substring(3, 6) + "-" + phoneNo.substring(6, 10);
            } else if (phoneNo.length() == 11) {
                buffer = phoneNo.substring(0, 3) + "-" + phoneNo.substring(3, 7) + "-" + phoneNo.substring(7, 11);
            }
        }
        return buffer;
    }

    public String addDashToBRN(String brn) {
        String buffer = brn;
        if (buffer != null && buffer.length() == 10) {
            buffer = brn.substring(0, 3) + "-" + brn.substring(3, 5) + "-" + brn.substring(5, 10);
        }
        return buffer;
    }
}
