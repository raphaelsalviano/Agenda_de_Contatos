package br.com.ufpb.ayty.contatos.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoLightTypeface extends TextView {

    public static final String TAG = "RobotoTextView";

    public RobotoLightTypeface(Context context) {
        super(context);
        alterarFonte(context);
    }

    public RobotoLightTypeface(Context context, AttributeSet attrs) {
        super(context, attrs);
        alterarFonte(context);
    }

    public RobotoLightTypeface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        alterarFonte(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RobotoLightTypeface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        alterarFonte(context);
    }

    private void alterarFonte(Context context){
        if(!isInEditMode()){ //Se nao estiver em modo de edicao
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_light.ttf");
            setTypeface(typeface);
        }
    }
}
