package uz.nasiba.avaz.memome.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleManager {
    private static String LANGUAGE_KEY = "LANGUAGE_KEY";

    static Context setLocale(Context context) {
        String savedLanguage = getLanguage(context);
        return savedLanguage == null ? context : updateResources(context, savedLanguage);
    }

    public static void setNewLocale(Context context, String language) {
        persistLanguage(context, language);
        updateResources(context, language);
    }

    private static void persistLanguage(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(LANGUAGE_KEY, language).apply();
    }

    private static String getLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Locale currentLocale = getLocale(context.getResources());
        return preferences.contains(LANGUAGE_KEY) ? preferences.getString(LANGUAGE_KEY, currentLocale.toString()) : null;
    }

    private static Locale getLocale(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}
