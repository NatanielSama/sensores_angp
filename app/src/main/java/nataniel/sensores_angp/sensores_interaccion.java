package nataniel.sensores_angp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class sensores_interaccion extends ActionBarActivity implements SensorEventListener{
    private SensorManager sensorManager = null;
    private Sensor sensorDeProximidad = null;
    private Sensor sensorDeLuz = null;
    private Sensor sensorAcelerometro = null;
    private Sensor Giroscopio = null;
    private TextView textAcelerometro = null;
    private TextView textGiroscopio = null;
    private TextView textProximidad = null;
    private TextView textLuz = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_interaccion);

        textAcelerometro = (TextView) findViewById(R.id.sensorAcelerometro);
        textAcelerometro.setTextSize(30);

        textGiroscopio = (TextView) findViewById(R.id.Giroscopio);
        textGiroscopio.setTextSize(30);

        textLuz = (TextView) findViewById(R.id.sensorDeLuz);
        textLuz.setTextSize(30);

        textProximidad = (TextView) findViewById(R.id.sensorDeProximidad);
        textProximidad.setTextSize(30);
    }
// Metodo para iniciar la escucha de los eventos de los sensores

    public void iniciar(){

        SensorManager sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listasen = sensorM.getSensorList(Sensor.TYPE_ALL);


        listasen = sensorM.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listasen.isEmpty()) {
            Sensor acel = listasen.get(0);
            sensorM.registerListener(this, acel,
                    SensorManager.SENSOR_DELAY_NORMAL);}

        listasen = sensorM.getSensorList(Sensor.TYPE_GYROSCOPE);

        if (!listasen.isEmpty()) {
            Sensor giroscopio = listasen.get(0);
            sensorM.registerListener(this, giroscopio,
                    SensorManager.SENSOR_DELAY_UI);}

        listasen = sensorM.getSensorList(Sensor.TYPE_PROXIMITY);

        if (!listasen.isEmpty()) {
            Sensor proximity = listasen.get(0);
            sensorM.registerListener(this, proximity,
                    SensorManager.SENSOR_DELAY_NORMAL);}


        listasen = sensorM.getSensorList(Sensor.TYPE_LIGHT);

        if (!listasen.isEmpty()) {
            Sensor luz = listasen.get(0);
            sensorM.registerListener(this, luz,
                    SensorManager.SENSOR_DELAY_NORMAL);}


    }
    public void detener(){
        SensorManager sensorManager1 = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listasen = sensorManager1.getSensorList(Sensor.TYPE_ALL);

        listasen = sensorManager1.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listasen.isEmpty()) {
            Sensor acel = listasen.get(0);
            sensorManager1.unregisterListener(this, sensorManager1.getDefaultSensor(acel.TYPE_ACCELEROMETER));
        }

        listasen = sensorManager1.getSensorList(Sensor.TYPE_GYROSCOPE);

        if (!listasen.isEmpty()) {
            Sensor giroscopio = listasen.get(0);
            sensorManager1.unregisterListener(this, sensorManager1.getDefaultSensor(giroscopio.TYPE_GYROSCOPE));
        }

        listasen = sensorManager1.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!listasen.isEmpty()) {
            Sensor proximity = listasen.get(0);
            sensorManager1.unregisterListener(this, sensorManager1.getDefaultSensor(proximity.TYPE_PROXIMITY));
        }

        listasen = sensorManager1.getSensorList(Sensor.TYPE_LIGHT);
        if (!listasen.isEmpty()) {
            Sensor luz = listasen.get(0);
            sensorManager1.unregisterListener(this, sensorManager1.getDefaultSensor(luz.TYPE_LIGHT));
        }

    }



    private void limpiar(){
       textAcelerometro.setText("");
        textGiroscopio.setText("");
        textLuz.setText("");
        textProximidad.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensores_interaccion, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.iniciar:
                iniciar();
                return true;
            case R.id.detener:
                detener();
                return true;
            case R.id.limpiar:
                limpiar();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }

}

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        synchronized (this){
            float[] masData;
            float x;
            float y;
            float z;
            // TODO Auto-generated method stub
            switch(arg0.sensor.getType()){
                case Sensor.TYPE_PROXIMITY:
                    masData = arg0.values;
                    x = masData[0];
                    textProximidad.setText("x: " + x);
                    textProximidad.setTextSize(25);

                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    masData = arg0.values;
                    x = masData[0];
                    y = masData[1];
                    z = masData[2];
                    textAcelerometro.setText("x: " + x + "\ny: "+y + "\nz: "+z);
                    textAcelerometro.setTextSize(25);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    masData = arg0.values;
                    x = masData[0];
                    y = masData[1];
                    z = masData[2];
                    textGiroscopio.setText("x: " + x + "\ny: "+y+"\nz:"+z);
                    break;
                case Sensor.TYPE_LIGHT:
                    masData = arg0.values;
                    x = masData[0];
                    textLuz.setText("x: " + x);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onStop();
    }
}
