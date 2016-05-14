package com.szr.jkxsx.teacher;

import java.security.Provider;

import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;

/**
 * Created by zczhang on 15/12/5.
 */
public class MyTrustManagerFactory extends TrustManagerFactory {

    /**
     * Creates a new {@code TrustManagerFactory} instance.
     *
     * @param factorySpi the implementation delegate.
     * @param provider   the provider
     * @param algorithm
     */
    protected MyTrustManagerFactory(TrustManagerFactorySpi factorySpi, Provider provider, String algorithm) {
        super(factorySpi, provider, algorithm);
    }


}
