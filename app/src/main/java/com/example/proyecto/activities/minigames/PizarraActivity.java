package com.example.proyecto.activities.minigames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.proyecto.R;
import com.example.proyecto.activities.LoginActivity;
import com.example.proyecto.activities.PacienteActivity;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;


public class PizarraActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout constraintLayout;
    private DrawingView drawView;
    //la elección del color de la paleta
    private ImageButton currPaint , drawBtn, eraseBtn, newBtn, saveBtn;
    //tamaño de pinceles
    private float smallBrush, mediumBrush, largeBrush;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizarra);
        constraintLayout = (LinearLayout)findViewById(R.id.root_pizarra);

        drawView = (DrawingView)findViewById(R.id.dibujo);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed,getApplicationContext().getTheme()));
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawBtn = (ImageButton)findViewById(R.id.btnPincel);
        drawBtn.setOnClickListener(this);
        //tamaño inicial del pincel
        drawView.setBrushSize(mediumBrush);
        //Borrador
        eraseBtn = (ImageButton)findViewById(R.id.btnBorrar);
        eraseBtn.setOnClickListener(this);
        //Generar archivos nuevos
        newBtn = (ImageButton)findViewById(R.id.btnNew);
        newBtn.setOnClickListener(this);
        //Guardar archivos
        saveBtn = (ImageButton)findViewById(R.id.btnGuardar);
        saveBtn.setOnClickListener(this);

    }
    public void paintClicked(View view){
        drawView.setErase(false);
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed, getApplicationContext().getTheme()));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint, getApplicationContext().getTheme()));
            currPaint=(ImageButton)view;
            //Vuelva a ajustar el tamaño del pincel al último utilizado al dibujar en lugar de borrar:
            drawView.setBrushSize(drawView.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnPincel){
            //draw button clicked
            tamanioPincel();
        }else if (v.getId()==R.id.btnBorrar) {
            tamanioGoma();
        }else if(v.getId()==R.id.btnNew){
            nuevoDibujo();
        }else if(v.getId()==R.id.btnGuardar){
            guardarDibujo();
        }
    }

    public void tamanioPincel(){
        final Dialog brushDialog = new Dialog(this);
        brushDialog.setTitle("Tamaño pincel:");
        brushDialog.setContentView(R.layout.brush_chooser);
        //Metemos los tamaños de los pinceles
        ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setBrushSize(smallBrush);
                drawView.setLastBrushSize(smallBrush);
                drawView.setErase(false);

                brushDialog.dismiss();
            }
        });
        ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setBrushSize(mediumBrush);
                drawView.setLastBrushSize(mediumBrush);
                drawView.setErase(false);

                brushDialog.dismiss();
            }
        });
        ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setBrushSize(largeBrush);
                drawView.setLastBrushSize(largeBrush);
                //DESPUES DE BORRAR PERMITA PINTAR DE NUEVO
                drawView.setErase(false);

                brushDialog.dismiss();
            }
        });
        brushDialog.show();
    }

    public void tamanioGoma(){
        final Dialog gomaDialog = new Dialog(this);
        gomaDialog.setTitle("Tamaño goma:");
        gomaDialog.setContentView(R.layout.brush_chooser);

        ImageButton smallBtn = (ImageButton)gomaDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setErase(true);
                drawView.setBrushSize(smallBrush);
                gomaDialog.dismiss();
            }
        });
        ImageButton mediumBtn = (ImageButton)gomaDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setErase(true);
                drawView.setBrushSize(mediumBrush);
                gomaDialog.dismiss();
            }
        });
        ImageButton largeBtn = (ImageButton)gomaDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setErase(true);
                drawView.setBrushSize(largeBrush);
                gomaDialog.dismiss();
            }
        });
        gomaDialog.show();
    }

    public void nuevoDibujo(){
        //new button
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("Nuevo dibujo");
        newDialog.setMessage("Comience un nuevo dibujo(quiere perder el anterior)?");
        newDialog.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                drawView.startNew();
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

    public void guardarDibujo(){
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Dibujo guardado");
        saveDialog.setMessage("Quiere guardar el dibujo en la galería?");
        saveDialog.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //save drawing
                drawView.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), drawView.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "dibujo");
                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Dibujo guardado!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "No se ha podido guardar.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
                drawView.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
    }

    //Código del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Construye la vista donde se va a colocar el menu
        getMenuInflater().inflate(R.menu.navigator_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId){
            case R.id.Item_home:
                Intent intent_home = new Intent(PizarraActivity.this, PacienteActivity.class);
                startActivity(intent_home);
                break;
            case R.id.Item_menusalir:
                Intent intent_salir = new Intent(PizarraActivity.this, LoginActivity.class);
                startActivity(intent_salir);
                break;
        }
        return true;
    }



}