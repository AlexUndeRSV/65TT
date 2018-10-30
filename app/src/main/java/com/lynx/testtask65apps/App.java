package com.lynx.testtask65apps;

import android.app.Application;

import com.lynx.testtask65apps.data.net.NetworkService;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class App extends Application {

    public static App INSTANCE;

    private Cicerone<Router> cicerone;

    private NetworkService networkService;


    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        cicerone = Cicerone.create();
        networkService = new NetworkService();
    }

    public static NavigatorHolder getNavigatorHolder() {
        return INSTANCE.cicerone.getNavigatorHolder();
    }

    public static Router getRouter() {
        return INSTANCE.cicerone.getRouter();
    }

    public static NetworkService getNetworkService() {
        return INSTANCE.networkService;
    }
}
