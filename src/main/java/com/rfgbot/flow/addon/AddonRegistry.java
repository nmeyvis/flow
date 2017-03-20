package com.rfgbot.flow.addon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public class AddonRegistry {
    private static AddonRegistry instance;
    private List<Addon> addons = new ArrayList<>();

    private AddonRegistry() {
    }

    // TODO: check for alias conflicts
    public AddonRegistry register(Addon addon) {
        addons.add(addon);
        return this;
    }

    public Addon find(String alias) {
        for(Addon addon : addons) {
            for(String a : addon.getAliases()) {
                if(a.equals(alias)) {
                    return addon;
                }
            }
        }
        
        return null;
    }

    public List<Addon> getAddons() {
        return new ArrayList<>(addons);
    }

    public static AddonRegistry getInstance() {
        if(instance == null) {
            instance = new AddonRegistry();
        }

        return instance;
    }
}
