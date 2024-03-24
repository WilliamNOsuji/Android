package org.osuji.exerciseserver;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osuji.exerciseserver.http.RetrofitUtil;
import org.osuji.exerciseserver.http.Service;

import static org.junit.Assert.*;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testRand() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.get();
        Call<String> call = service.randomNum();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RetroFit", resultat);
    }
}