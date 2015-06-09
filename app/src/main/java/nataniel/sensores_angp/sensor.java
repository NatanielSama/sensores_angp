package nataniel.sensores_angp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public  class sensor extends ActionBarActivity implements SensorEventListener {
    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        salida = (TextView) findViewById(R.id.salida);

        SensorManager sensorManager1 = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listasensores = sensorManager1.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor: listasensores) {
            Log(sensor.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.nuevosen:
                Intent intent = new Intent(getBaseContext(), sensores_interaccion.class);
                startActivity(intent);
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }
    @Override
    public void onSensorChanged(SensorEvent arg0) {

    }

private void Log(String string){
    salida.append(string+"\n");
}
}