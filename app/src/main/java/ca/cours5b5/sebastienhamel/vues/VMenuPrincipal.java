package ca.cours5b5.sebastienhamel.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

public class VMenuPrincipal extends Vue {


    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    static{

        Log.d("Atelier04","VMenuPrincipal :: static");

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("Atelier04","VMenuPrincipal :: onFinishInflate");
    }
}
