package org.osuji.exerciseretrofitsimple;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osuji.exerciseretrofitsimple.http.RetrofitUtil;
import org.osuji.exerciseretrofitsimple.http.RetrofitUtilGithub;
import org.osuji.exerciseretrofitsimple.http.Service;
import org.osuji.exerciseretrofitsimple.transfer.ModelComplex;
import org.osuji.exerciseretrofitsimple.transfer.ModelGithub;
import org.osuji.exerciseretrofitsimple.transfer.ModelList;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

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
    public void testSimple() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.get();
        Call<String> call = service.doubleOfNum("2");
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RetroFit", resultat);
    }

    @Test
    public void testComplex() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.get();
        Call<ModelComplex> call = service.objetComplex("william");
        Response<ModelComplex> response = call.execute();
        ModelComplex resultat = response.body();
        Log.i("RetroFit", resultat.toString());
    }

    @Test
    public void testListLong() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.get();
        Call<List<Long>> call = service.longList();
        Response<List<Long>> response = call.execute();
        List<Long> resultat = response.body();
        Log.i("RetroFit", resultat.toString());
    }

    @Test
    public void testListObject() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtil.get();
        Call<List<ModelList>> call = service.objectList();
        Response<List<ModelList>> response = call.execute();
        List<ModelList> resultat = response.body();
        Log.i("RetroFit", resultat.toString());
    }

    @Test
    public void testListGithub() throws IOException {
        // Context of the app under test.
        Service service = RetrofitUtilGithub.get();
        Call<List<ModelGithub>> call = service.infoGit();
        Response<List<ModelGithub>> response = call.execute();
        List<ModelGithub> resultat = response.body();
        Log.i("RetroFit", resultat.toString());
    }
}