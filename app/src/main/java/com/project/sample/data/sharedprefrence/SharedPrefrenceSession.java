package com.project.sample.data.sharedprefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.project.sample.R;
import com.project.sample.data.model.HadithSettingsModel;
import com.project.sample.data.model.TranslationLanguage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SharedPrefrenceSession {
    final SharedPreferences pref;
    final SharedPreferences.Editor editor;
    final Context _context;

    // shared pref mode
    final int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "muslimquranpro";
    private static final String HADITH_SETTINGS = "hadith_settings";
    private static final String TRANSLATION_LANGUAGES = "translation_languages";
    private static final String HADITH_RECENT_SEARCH = "hadith_recent_search";
    private static final String HADITH_SEARCH_BOOK = "hadith_search_book";

    public SharedPrefrenceSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public List<TranslationLanguage> getTranslationLanguages(){
        if (pref.getString(TRANSLATION_LANGUAGES, null) == null) {
            List<TranslationLanguage> languageList = new ArrayList<>();
            TranslationLanguage language = new TranslationLanguage(1,
                    "ic_flag_english",
                    "English","English", "en",
                    R.font.sf_pro_text_medium,
                    true);
            languageList.add(language);
            language = new TranslationLanguage(2,
                    "ic_flag_french",
                    "Français","French", "fr",
                     R.font.sf_pro_text_medium,
                    false);
            languageList.add(language);
            language = new TranslationLanguage(3,
                    "ic_flag_nederland",
                    "Nederlands","Dutch", "nl",
                     R.font.sf_pro_text_medium,
                    false);
            languageList.add(language);
            language = new TranslationLanguage(4,
                    "ic_flag_chinese",
                    "中文（繁体)","Chinese (Simplified)", "zh-CN",
                     R.font.sf_pro_text_medium,
                    false);
            languageList.add(language);
            language = new TranslationLanguage(5,
                    "ic_flag_arabic",
                    "العربية","Arabic","ar",
                    R.font.adobe_arabic_bold,
                    false);
            languageList.add(language);
//        language = new TranslationListAdapter.Language(6,
//                "ic_flag_bangali",
//                "বাঙালি","Banglai",
//                ResourcesCompat.getFont(this, R.font.sf_pro_text_medium),
//                false);
//        languageList.add(language);
            setTranslationLanguages(languageList);
        }
        Gson gson = new Gson();
        return gson.fromJson(pref.getString(TRANSLATION_LANGUAGES, null),
                new TypeToken<List<TranslationLanguage>>(){}.getType());

    }

    public void setTranslationLanguages(List<TranslationLanguage> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(TRANSLATION_LANGUAGES, json);
        editor.apply();
    }

    public HadithSettingsModel getHadithSettings(){
        HadithSettingsModel obj = new HadithSettingsModel();
        if (pref.getString(HADITH_SETTINGS, null) == null)
            setHadithSettings(obj);
        Gson gson = new Gson();
        return gson.fromJson(pref.getString(HADITH_SETTINGS, null), HadithSettingsModel.class);
    }

    public void setHadithSettings(HadithSettingsModel object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(HADITH_SETTINGS, json);
        editor.apply();
    }

    public List<String> getHadithRecentSearch() {
        Gson gson = new Gson();
        return gson.fromJson(pref.getString(HADITH_RECENT_SEARCH, null),
                new TypeToken<List<String>>(){}.getType());
    }

    public void setHadithRecentSearch(List<String> recent) {
        Gson gson = new Gson();
        String json = gson.toJson(recent);
        editor.putString(HADITH_RECENT_SEARCH, json).apply();
    }

    public List<Integer> getHadithSearchBook() {
        Gson gson = new Gson();
        return gson.fromJson(pref.getString(HADITH_SEARCH_BOOK, null),
                new TypeToken<List<Integer>>(){}.getType());
    }

    public void setHadithSearchBook(List<Integer> books) {
        Gson gson = new Gson();
        String json = gson.toJson(books);
        editor.putString(HADITH_SEARCH_BOOK, json).apply();
    }

}
