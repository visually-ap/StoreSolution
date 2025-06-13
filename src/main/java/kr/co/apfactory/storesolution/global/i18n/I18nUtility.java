package kr.co.apfactory.storesolution.global.i18n;

import kr.co.apfactory.storesolution.global.cookie.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class I18nUtility {
    @Autowired
    private CookieManager cookieManager;

    public String getLanguage(HttpServletRequest request) {
        String lang = cookieManager.getCookie(request, "lang");
        if (StringUtils.isEmpty(lang)) {
            lang = LocaleContextHolder.getLocale().toString();
        }

        return lang;
    }
}
