package com.example.proyecto.activities.minigames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.proyecto.R;

import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;


public class PizarraActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawingView drawingView;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn;
    private ImageButton color1,color2,color3,color4,color5,color6,color7, color8 ,color9, color10 , color11 ,color12;
    private float smallBrush, mediumBrush, largeBrush;

    //array de colores
    int[] colores;
    //array imageButton de paleta
    ImageButton[] paletacolores = new ImageButton[12];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizarra);

        drawingView = (DrawingView) findViewById(R.id.dibujo);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
       // currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed,getApplicationContext().getTheme()));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawBtn = (ImageButton)findViewById(R.id.btnPincel);
        drawBtn.setOnClickListener(this);


        paletacolores[0] = (ImageButton) findViewById(R.id.colorpaleta1);
        paletacolores[1] = (ImageButton) findViewById(R.id.colorpaleta2);
        paletacolores[2] = (ImageButton) findViewById(R.id.colorpaleta3);
        paletacolores[3] = (ImageButton) findViewById(R.id.colorpaleta4);
        paletacolores[4] = (ImageButton) findViewById(R.id.colorpaleta5);
        paletacolores[5] = (ImageButton) findViewById(R.id.colorpaleta6);
        paletacolores[6] = (ImageButton) findViewById(R.id.colorpaleta7);
        paletacolores[7] = (ImageButton) findViewById(R.id.colorpaleta8);
        paletacolores[8] = (ImageButton) findViewById(R.id.colorpaleta9);
        paletacolores[9] = (ImageButton) findViewById(R.id.colorpaleta10);
        paletacolores[10] = (ImageButton) findViewById(R.id.colorpaleta11);
        paletacolores[11] = (ImageButton) findViewById(R.id.colorpaleta12);

        for (ImageButton color : paletacolores) {
            color.setOnClickListener(this);
        }
        cargarColores();
        paintClicled(color1);
        //Comprobar
        drawingView.setBrushSize(mediumBrush);
        drawingView.setLastBrushSize(mediumBrush);
        drawingView.getLastBrushSize();

        eraseBtn = (ImageButton)findViewById(R.id.btnBorrar);
        eraseBtn.setOnClickListener(this);

        newBtn = (ImageButton)findViewById(R.id.btnNew);
        newBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.btnGuardar);
        saveBtn.setOnClickListener(this);

    }

    private void cargarColores() {

        colores = new int[]{
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,
                R.drawable.paint,


        };

        for (int i = 0; i < paletacolores.length; i++) {
            paletacolores[i].setImageResource(colores[i]);
            paletacolores[i].setOnClickListener(this);
        }

    }




    public void paintClicled(View view) {
        drawingView.setErase(false);

        if (view != currPaint) {
            ImageButton imgView = (ImageButton) view;
            int colorIndex = Integer.parseInt(view.getTag().toString()) - 1;
            int colorResource = colores[colorIndex];

            drawingView.setColor(String.valueOf(colorResource));
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed, getApplicationContext().getTheme()));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint, getApplicationContext().getTheme()));
            currPaint = (ImageButton) view;
            drawingView.setBrushSize(drawingView.getLastBrushSize());
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnPincel){
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Tamaño pincel:");
            brushDialog.setContentView(R.layout.brush_chooser);
            //Pincel pequeño
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawingView.setBrushSize(smallBrush);
                    drawingView.setLastBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            //Pincel mediano
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawingView.setBrushSize(mediumBrush);
                    drawingView.setLastBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            //Pincel grande
            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawingView.setBrushSize(largeBrush);
                    drawingView.setLastBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        //si no quiere usar el pincel usa la goma
        }else if(v.getId()!=R.id.btnPincel) {
            if(v.getId()==R.id.btnBorrar){
                final Dialog brushDialog = new Dialog(this);
                brushDialog.setTitle("Tamaño goma:");
                brushDialog.setContentView(R.layout.brush_chooser);
                //Tmaño pequeño
                ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawingView.setErase(true);
                        drawingView.setBrushSize(smallBrush);
                        brushDialog.dismiss();
                        drawingView.setErase(false);

                    }
                });
                //Tamaño mediano
                ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawingView.setErase(true);
                        drawingView.setBrushSize(mediumBrush);
                        brushDialog.dismiss();
                        drawingView.setErase(false);

                    }
                });
                //Tamaño grande
                ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawingView.setErase(true);
                        drawingView.setBrushSize(largeBrush);
                        brushDialog.dismiss();
                        drawingView.setErase(false);

                    }
                });
                brushDialog.show();
            }else if(v.getId()==R.id.btnNew){
                //Asegurarnos que se va a iniciar un nuevo dibujo
                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("Nuevo dibujo");
                newDialog.setMessage("Empezar nuebo dibujo(quiere perder el anterior dibujo)?");
                newDialog.setPositiveButton("Si", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        drawingView.startNew();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                newDialog.show();
            }
        }else if(v.getId()==R.id.btnGuardar){
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Guardar");
            saveDialog.setMessage("Quiere guardar la imagen ?");
            saveDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawingView.setDrawingCacheEnabled(true);
                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(), drawingView.getDrawingCache(),
                            UUID.randomUUID().toString()+".png", "dibujo");
                    if(imgSaved!=null){
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Dibujo guardado en la galería", Toast.LENGTH_SHORT);
                        savedToast.show();
                    }
                    else{
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Oops! No se ha guardado.", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    drawingView.destroyDrawingCache();

                }
            });
            saveDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }

    }
}