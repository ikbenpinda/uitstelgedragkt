package achan.nl.uitstelgedrag.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;

/**
 * Created by Etienne on 16-11-2016.
 */
public class Settings {

    public static final String PREFS_THEME                 = "Theme";
    public static final String PREFS_NOTIFICATIONS_ENABLED = "Notifications";
    public static final String PREFS_MICROPHONE_TRIGGER    = "Microphonesensor";

    // important - see Themes.java.
    public static final int     THEME_DEFAULT              = 0;
    public static final int     THEME_LIGHT                = 0;
    public static final int     THEME_DARK                 = 1;
    public static final int     THEME_AUTO                 = 2;
    public static final int     MICROPHONE_TRIGGER_DEFAULT = Sensor.TYPE_PROXIMITY;
    public static final boolean NOTIFY_ME_DEFAULT          = false;

    private int               theme;
    private boolean           useProximityScanner;
    private boolean           notifyMe;
    private String            wakeUpTime;
    private Context           context;
    private SharedPreferences preferences;

    public Settings(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("Uitstelgedrag,userprefs", Context.MODE_PRIVATE);
    }

    public int getTheme() {
        return preferences.getInt(PREFS_THEME, THEME_DEFAULT);
    }

    public boolean setTheme(int theme) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREFS_THEME, theme);
        return editor.commit();
    }

    /**
     * important: Experimental shortcut.
     * important: generics?
     */
    public Object read(Object preference) {
        return preferences.getAll().get(preference);
    }

    /**
     * important: Experimental shortcut.
     */
    public void write(EditCommand command) {
        SharedPreferences.Editor editor = preferences.edit();
        command.edit(editor);
        editor.commit();
    }

    public interface EditCommand {
        void edit(SharedPreferences.Editor editor);
    }
}

