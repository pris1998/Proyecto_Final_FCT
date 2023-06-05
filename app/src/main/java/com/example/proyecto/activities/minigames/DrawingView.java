package com.example.proyecto.activities.minigames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.MotionEvent;

import com.example.proyecto.R;


public class DrawingView extends View {

    //La ruta de dibujo
    private Path drawPath;
    // Los pinceles para dibujar y para el lienzo
    private Paint drawPaint, canvasPaint;
    // El color inicial
    private int paintColor = 0xFF660000;
    // El lienzo
    private Canvas drawCanvas;
    //El mapa de bits del lienzo
    private Bitmap canvasBitmap;

    // El tamaño del pincel y el último tamaño de pincel utilizado
    private float brushSize, lastBrushSize;
    // Variable para borrar
    private boolean erase=false;

    /**
     * Constructor de la clase.
     * @param context El contexto de la aplicación.
     * @param attrs Atributos XML.
     */
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    /**
     * Configura los elementos necesarios para el dibujo.
     * Inicializa la ruta de dibujo, los pinceles y el lienzo.
     */
    private void setupDrawing(){
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);

        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);

    }

    /**
     * Establece el tamaño del pincel.
     * @param newSize El nuevo tamaño del pincel.
     */
    public void setBrushSize(float newSize){
        // Actualiza el tamaño
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    /**
     * Establece el último tamaño de pincel utilizado.
     * @param lastSize El último tamaño de pincel.
     */
    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }

    /**
     * Obtiene el último tamaño de pincel utilizado.
     * @return El último tamaño de pincel.
     */
    public float getLastBrushSize(){
        return lastBrushSize;
    }

    /**
     * Establece si se está borrando o no.
     * @param isErase Indica si se está borrando.
     */
    public void setErase(boolean isErase){
        erase=isErase;
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);
    }

    /**
     * Comienza un nuevo dibujo.
     * Borra el lienzo y lo actualiza.
     */
    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Establecer tamaño fijo (200x200 píxeles)
        int desiredWidth = 200;
        int desiredHeight = 200;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        // Establecer el tamaño de la vista dependiendo del modo de medida
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        // Establecer las dimensiones medidas de la vista
        setMeasuredDimension(width, height);
    }

    /**
     * Maneja el evento de tocar la pantalla.
     * Permite al usuario dibujar en la vista siguiendo el movimiento del dedo.
     * @param event El evento táctil.
     * @return Verdadero si el evento fue manejado, falso de lo contrario.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    /**
     * Establece el color de dibujo de la parcela de colores
     * @param newColor El nuevo color en formato hexadecimal.
     */
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

}
