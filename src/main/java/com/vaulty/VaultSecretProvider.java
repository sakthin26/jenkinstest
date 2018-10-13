package com.vaulty;

import com.bettercloud.vault.SslConfig;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.api.Logical;
import java.util.HashMap;
import java.util.Map;

public class VaultSecretProvider {

   // private static final Logger logger = LoggerFactory.getLogger("com.intuit.karate");

    public static Map<String, String> getSecretsFromVault(String path) {

        Map<String, String> secrets = new HashMap<>();
        try {

            VaultConfig vaultConfig = new VaultConfig()
                    .address(System.getenv("VAULT_URL"))
                    .token(System.getenv("VAULT_TOKEN"))
                    .sslConfig(new SslConfig().verify(true).build())
                    .build();

            Logical vaultLogicalOps = new Vault(vaultConfig)
                    .logical();

            secrets.putAll(vaultLogicalOps.read(System.getenv(path)).
                    getData());

        } catch (Exception e) {
            //logger.info("There was an error connecting to vault");
        }

        return secrets;
    }

    public static Map<String, String> getLoginCredentials() {
        return getSecretsFromVault("static/services/lithium");
    }

    public static void main(String[] args) {
        String sdd = System.getenv("ABCD");
        System.out.println("env"+sdd);
        System.out.println("This is java app \n by using Docker");
    }
}