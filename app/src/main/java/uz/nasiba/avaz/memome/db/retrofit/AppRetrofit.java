package uz.nasiba.avaz.memome.db.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.nasiba.avaz.memome.db.retrofit.service.AccountService;

public class AppRetrofit {
    private Retrofit instance;
    private AccountService accountService;

    public AppRetrofit() {
        instance = new Retrofit.Builder()
                .baseUrl("http://nasiba.me:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        accountService = instance.create(AccountService.class);
    }

    public Retrofit getInstance() {
        return instance;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
