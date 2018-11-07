package com.lynx.testtask65apps;

import android.app.Application;

import com.lynx.testtask65apps.data.net.NetworkService;
import com.lynx.testtask65apps.data.repositories.DBRepository;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class App extends Application {

    public static App INSTANCE;

    private final Cicerone<Router> cicerone = Cicerone.create();

    private final NetworkService networkService = new NetworkService();

    private final DBRepository dbRepository = new DBRepository(this);

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
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

    public static DBRepository getDBRepository() {
        return INSTANCE.dbRepository;
    }
}
