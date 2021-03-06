/**
 * Copyright (C) 2009-2015 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.network;

import org.dasein.cloud.Taggable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
public class Vpn implements Taggable {
    private VpnState           currentState;
    private String             description;
    private String             name;
    private VpnProtocol        protocol;
    private String[]           providerVlanIds;
    private String             providerVpnId;
    private String             providerVpnIp;
    private String             providerRegionId;
    private Map<String,String> tags;

    public Vpn() { }

    public VpnState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VpnState currentState) {
        this.currentState = currentState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VpnProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(VpnProtocol protocol) {
        this.protocol = protocol;
    }

    public String getProviderVpnId() {
        return providerVpnId;
    }

    public void setProviderVpnId(String providerVpnId) {
        this.providerVpnId = providerVpnId;
    }

    public @Nonnull Map<String, String> getTags() {
        if( tags == null ) {
            tags = new HashMap<String, String>();
        }
        return tags;
    }

    public void setTag(@Nonnull String key, @Nonnull String value) {
        getTags().put(key, value);
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public @Nonnull String[] getProviderVlanIds() {
        return (providerVlanIds == null ? new String[0] : providerVlanIds);
    }

    public void setProviderVlanIds(String[] providerVlanIds) {
        this.providerVlanIds = providerVlanIds;
    }


    public String toString() {
        return providerVpnId;
    }

    public String getProviderVpnIp() {
        return providerVpnIp;
    }

    public @Nullable String getProviderRegionId() {
        return providerRegionId;
    }

    public void setProviderRegionId(@Nullable String providerRegionId) {
        this.providerRegionId = providerRegionId;
    }

    public void setProviderVpnIP(String providerVpnIp) {
        this.providerVpnIp = providerVpnIp;
    }
}
