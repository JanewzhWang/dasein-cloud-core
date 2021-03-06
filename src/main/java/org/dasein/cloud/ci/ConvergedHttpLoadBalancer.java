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

package org.dasein.cloud.ci;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.dasein.cloud.Taggable;

/**
 * Options for creating a new ConvergedHttpLoadBalancer.
 */
public class ConvergedHttpLoadBalancer implements Taggable {
    private String name;
    private String description;
    private String selfLink;
    private String creationTimestamp;
    private String defaultBackendService;
    private List<UrlSet> urlSets = new ArrayList<UrlSet>();
    private List<TargetHttpProxy> targetHttpProxies = new ArrayList<TargetHttpProxy>();
    private List<ForwardingRule> forwardingRules = new ArrayList<ForwardingRule>();
    private List<BackendService> backendServices = new ArrayList<BackendService>();
    private List<HealthCheck> healthChecks = new ArrayList<HealthCheck>();
    private List<BackendServiceBackend> backendServiceBackends = new ArrayList<BackendServiceBackend>();

    /**
     * @return a list of backend service backends, these should be the instance pools behind the backend services.
     */
    public List<BackendServiceBackend> getBackendServiceBackends() {
        return backendServiceBackends;
    }

    /**
     * @return a list of healthchecks in use by this http load balancer.
     */
    public List<HealthCheck> getHealthChecks() {
        return healthChecks;
    }

    /**
     * @return a list of backend services in use by this http load balancer.
     */
    public List<BackendService> getBackendServices() {
        return backendServices;
    }

    /**
     * @return a list of forwarding rules in use by this http load balancer.
     */
    public List<ForwardingRule> getForwardingRules() {
        return forwardingRules;
    }

    /**
     * target proxies bind a http/https port with a urlMap
     * @return a list of target proxies in use by this http load balancer.
     */
    public List<TargetHttpProxy> getTargetHttpProxies() {
        return targetHttpProxies;
    }

    /**
     * @return a list of url sets in use by this http load balancer.
     * Url sets govern which backend service will service the request
     * based on the url path or hostname being requested from
     */
    public List<UrlSet> getUrlSets() {
        return urlSets;
    }

    /**
     * Constructor for converged http load balancer
     */
    private ConvergedHttpLoadBalancer(String name, String description, String selfLink, String creationTimestamp, String defaultBackendService) { 
        this.name = name;
        this.description = description;
        this.selfLink = selfLink;
        this.creationTimestamp = creationTimestamp;
        this.defaultBackendService = defaultBackendService;
    }

    /**
     * Factory method for returning a new converged http load balancer.
     * This version is used when cataloging existing http load balancers.
     * @param name the name for the converged http load balancer
     * @param description the description for the converged http load balancer
     * @param selfLink a server-defined fully-qualified URL for this resource.
     * @param creationTimestamp the timestamp for when the http load balancer was created
     * @param defaultBackendService the default backend service to direct to when there are no rules specifically overriding
     * @return a new new ConvergedHttpLoadBalancer
     */
    static public @Nonnull ConvergedHttpLoadBalancer getInstance(String name, String description, String selfLink, String creationTimestamp, String defaultBackendService) {
        return new ConvergedHttpLoadBalancer(name, description, selfLink, creationTimestamp, defaultBackendService);
    }

    /**
     * Factory method for returning a new converged http load balancer.
     * This version is used when creating a new http load balancer.
     * @param name the name for the converged http load balancer
     * @param description the description for the converged http load balancer
     * @param defaultBackendService the default backend service to direct to when there are no rules specifically overriding
     * @return a new new ConvergedHttpLoadBalancer
     */
    static public @Nonnull ConvergedHttpLoadBalancer getInstance(String name, String description, String defaultBackendService) {
        return new ConvergedHttpLoadBalancer(name, description, null, null, defaultBackendService);
    }

    public class UrlSet {
        private String name;
        private String description;
        private String hostMatchPatterns;
        private Map<String, String> pathMap;

        /**
         * Constructor for converged http load balancer UrlSet
         */
        UrlSet(String name, String description, String hostMatchPatterns, Map<String, String> pathMap) {
            this.name = name;
            this.description = description;
            this.hostMatchPatterns = hostMatchPatterns;
            this.pathMap = pathMap;
        }

        /**
         * Get converged http loadbalancer urlSet name
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * Get converged http loadbalancer URL set description
         * @return description of the URL set
         */
        public String getDescription() {
            return description;
        }

        /**
         * Get converged http loadbalancer URL set host match pattern
         * @return host match pattern of the URL set
         */
        public String getHostMatchPatterns() {
            return hostMatchPatterns;
        }

        /**
         * Get converged http loadbalancer URL set path map
         * @return path map of the URL set
         */
        public Map<String, String> getPathMap() {
            return pathMap;
        }
    }

    /**
     * Factory method for attaching a new url set to a converged http load balancer.
     * @param name the name for the url set
     * @param description the description for the url set
     * @param hostMatchPatterns the host match pattern. * is a wild card.
     * @param pathMap a map of path roots to backend services
     * @return this
     */
    public ConvergedHttpLoadBalancer withUrlSet(String name, String description, String hostMatchPatterns, Map<String, String> pathMap) {
        urlSets.add(new UrlSet(name, description, hostMatchPatterns, pathMap));
        return this;
    }

    public class TargetHttpProxy {
        private String name;
        private String description;
        private String creationTimestamp;
        private String selfLink;

        TargetHttpProxy(String name, String description, String creationTimestamp, String selfLink) {
            this.name = name;
            this.description = description;
            this.creationTimestamp = creationTimestamp;
            this.selfLink = selfLink;
        }

        /**
         * Get converged http loadbalancer targetHttpProxy name
         * @return name of the targetHttpProxy
         */
        public String getName() {
            return name;
        }

        /**
         * Get converged http loadbalancer target Http proxy description
         * @return description of the target Http proxy
         */
        public String getDescription() {
            return description;
        }

        /**
         * Get converged http loadbalancer target Http proxy creation time stamp
         * @return creationTimestamp of the target Http proxy
         */
        public String getCreationTimestamp() {
            return creationTimestamp;
        }

        /**
         * Get converged http loadbalancer target Http proxy self link
         * @return self link of the target Http proxy
         */
        public String getSelfLink() {
            return selfLink;
        }

        /**
         * Set converged http loadbalancer target Http proxy self link
         * @param selfLink of the target Http proxy
         */
        public void setTargetProxySelfUrl(String selfLink) {
            this.selfLink = selfLink;
        }
    }

    /**
     * Factory method for attaching a existing target http proxy to a converged http load balancer.
     * This version is used when cataloging existing http load balancers.
     * @param name the name for the target http proxy
     * @param description the description for the target http proxy
     * @param creationTimestamp the timestamp for when the target proxy was created
     * @param selfLink a server-defined fully-qualified URL for this resource.
     * @return this
     */
    public ConvergedHttpLoadBalancer withTargetHttpProxy(String name, String description, String creationTimestamp, String selfLink) {
        targetHttpProxies.add(new TargetHttpProxy(name, description, creationTimestamp, selfLink));
        return this;
    }

    /**
     * Factory method for attaching a new target http proxy to a converged http load balancer.
     * This version is used when creating a new http load balancer.
     * @param name the name for the target http proxy
     * @param description the description for the target http proxy
     * @return this
     */
    public ConvergedHttpLoadBalancer withTargetHttpProxy(String name, String description) {
        targetHttpProxies.add(new TargetHttpProxy(name, description, null, null));
        return this;
    }

    public class ForwardingRule {
        private String name;
        private String description;
        private String creationTimestamp;
        private String selfLink;
        private String ipAddress;
        private String ipProtocol;
        private String portRange;
        private String target;

        ForwardingRule(String name, String description, String creationTimestamp, String ipAddress, String ipProtocol, String portRange, String selfLink, String target) {
            this.name = name;
            this.description = description;
            this.creationTimestamp = creationTimestamp;
            this.ipAddress = ipAddress;
            this.ipProtocol = ipProtocol;
            this.portRange = portRange;
            this.selfLink = selfLink;
            this.target = target;
        }

        /**
         * Get converged http loadbalancer forwardingRule name
         * @return name of the forwardingRule
         */
        public String getName() {
            return name;
        }

        /**
         * Get converged http loadbalancer forwarding rule description
         * @return description of the forwarding rule
         */
        public String getDescription() {
            return description;
        }

        /**
         * Get converged http loadbalancer forwarding rule creation time stamp
         * @return getCreationTimestamp of the forwarding rule
         */
        public String getCreationTimestamp() {
            return creationTimestamp;
        }

        /**
         * Get converged http loadbalancer forwarding rule self link
         * @return selfLink of the forwarding rule
         */
        public String getSelfLink() {
            return selfLink;
        }

        /**
         * Set converged http loadbalancer forwarding rule self link
         * @param selfLink of the forwarding rule
         */
        public void setGlobalForwardingRuleSelfUrl(String selfLink) {
            this.selfLink = selfLink;
        }

        /**
         * Get converged http loadbalancer forwarding rule IP address
         * @return ipAddress of the forwarding rule
         */
        public String getIpAddress() {
            return ipAddress;
        }

        /**
         * Get converged http loadbalancer forwarding rule IP protocol
         * @return ipAddress of the forwarding rule
         */
        public String getIpProtocol() {
            return ipProtocol;
        }

        /**
         * Get converged http loadbalancer forwarding rule port range
         * @return portRange of the forwarding rule
         */
        public String getPortRange() {
            return portRange;
        }

        /**
         * Get converged http loadbalancer forwarding rule target
         * @return target of the forwarding rule
         */
        public String getTarget() {
            return target;
        }
    }

    /**
     * Factory method for attaching a existing forwarding rule to a converged http load balancer.
     * This version is used when cataloging existing http load balancers.
     * @param name the name for the forwarding rule
     * @param description the description for the forwarding rule
     * @param creationTimestamp the timestamp for when the forwarding rule was created
     * @param ipAddress the ip address allocated to the forwarding rule
     * @param ipProtocol the protocol used by the forwarding rule
     * @param portRange this can be either 80 or 8080
     * @param selfLink a server-defined fully-qualified URL for this resource
     * @param target the target proxy this rule forwards to
     * @return this
     */
    public ConvergedHttpLoadBalancer withForwardingRule(String name, String description, String creationTimestamp, String ipAddress, String ipProtocol, String portRange, String selfLink, String target) {
        forwardingRules.add(new ForwardingRule(name, description, creationTimestamp, ipAddress, ipProtocol, portRange, selfLink, target));
        return this;
    }

    /**
     * Factory method for attaching a new forwarding rule to a converged http load balancer.
     * This version is used when creating a new http load balancer.
     * @param name the name for the forwarding rule
     * @param description the description for the forwarding rule
     * @param ipAddress the ip address allocated to the forwarding rule
     * @param ipProtocol this is either HTTP or HTTPS
     * @param portRange this can be either 80 or 8080
     * @param target the target proxy this rule forwards to
     * @return this
     */
    public ConvergedHttpLoadBalancer withForwardingRule(String name, String description, String ipAddress, String ipProtocol, String portRange, String target) {
        forwardingRules.add(new ForwardingRule(name, description, null, ipAddress, ipProtocol, portRange, null, target));
        return this;
    }

    public class BackendService {
        private String name;
        private String description;
        private String creationTimestamp;
        private Integer port;
        private String portName;
        private String protocol;
        private String[] healthChecks;
        private String selfLink;
        private Integer timeoutSec;
        private String[] backendServiceBackends;

        BackendService(String name, String description, String creationTimestamp, Integer port, String portName, String protocol, String[] healthChecks, String[] backendServiceBackends, String selfLink, Integer timeoutSec) {
            this.name = name;
            this.description = description;
            this.creationTimestamp = creationTimestamp;
            this.port = port;
            this.portName = portName;
            this.protocol = protocol;
            this.healthChecks = healthChecks;
            this.backendServiceBackends = backendServiceBackends;
            this.selfLink = selfLink;
            this.timeoutSec = timeoutSec;
        }

        /**
         * Get converged http loadbalancer backendService name
         * @return name of the backend service
         */
        public String getName() {
            return name;
        }

        /**
         * Get converged http loadbalancer backend service description
         * @return description of the backend service
         */
        public String getDescription() {
            return description;
        }

        /**
         * Get converged http loadbalancer backend service creation time stamp
         * @return creationTimestamp of the backend service
         */
        public String getCreationTimestamp() {
            return creationTimestamp;
        }

        /**
         * Get converged http loadbalancer backend service port
         * @return port of the backend service
         */
        public Integer getPort() {
            return port;
        }

        /**
         * Get converged http loadbalancer backend service port name
         * @return portName of the backend service
         */
        public String getPortName() {
            return portName;
        }

        /**
         * Get converged http loadbalancer backend service protocol
         * @return protocol of the backend service
         */
        public String getProtocol() {
            return protocol;
        }

        /**
         * Get converged http loadbalancer backend service health checks
         * @return healthChecks of the backend service
         */
        public String[] getHealthChecks() {
            return healthChecks;
        }

        /**
         * Get converged http loadbalancer backend service backend service backends
         * @return backendServiceBackends of the backend service
         */
        public String[] getBackendServiceBackends() {
            return backendServiceBackends;
        }

        /**
         * Set converged http loadbalancer backend service service url
         * @param serviceUrl of the backend service
         */
        public void setServiceUrl(String serviceUrl) {
            selfLink = serviceUrl;
        }

        /**
         * Get converged http loadbalancer backend service self link
         * @return selfLink of the backend service
         */
        public String getSelfLink() {
            return selfLink;
        }

        /**
         * Get converged http loadbalancer backend service timeout in seconds
         * @return timeoutSec of the backend service
         */
        public Integer getTimeoutSec() {
            return timeoutSec;
        }
    }

    /**
     * Factory method for attaching a existing backend service to a converged http load balancer.
     * This version is used when cataloging existing http load balancers.
     * @param name the name of the backend service
     * @param description the description of the backend service
     * @param creationTimestamp the timestamp for when the backend service was created
     * @param port the port for the exchange
     * @param portName the name assigned to the port
     * @param protocol the protocol used by the backend service
     * @param healthChecks the names of health checks associated with the backend service
     * @param backendServiceBackends the names of the backend service's backends
     * @param selfLink a server-defined fully-qualified URL for this resource.
     * @param timeoutSec how long to wait for the backend service to respond before considering the request failed
     * @return this
     */
    public ConvergedHttpLoadBalancer withBackendService(String name, String description, String creationTimestamp, Integer port, String portName, String protocol, String[] healthChecks, String[] backendServiceBackends, String selfLink, Integer timeoutSec) {
        backendServices.add(new BackendService(name, description, creationTimestamp, port, portName, protocol, healthChecks, backendServiceBackends, selfLink, timeoutSec));
        return this;
    }

    /**
     * Factory method for attaching a new backend service to a converged http load balancer.
     * This version is used when creating a new http load balancer.
     * @param name the name for the backend service
     * @param description the description for the backend service
     * @param port the port for the exchange
     * @param portName the name assigned to the port
     * @param protocol the protocol the backend service will use
     * @param healthChecks the names of health checks associated with the backend service
     * @param backendServiceBackends  the names of the backend service's backends
     * @param timeoutSec how long to wait for the backend service to respond before considering the request failed
     * @return this
     */
    public ConvergedHttpLoadBalancer withBackendService(String name, String description, Integer port, String portName, String protocol, String[] healthChecks, String[] backendServiceBackends, Integer timeoutSec) {
        backendServices.add(new BackendService(name, description, null, port, portName, protocol, healthChecks, backendServiceBackends, null, timeoutSec));
        return this;
    }

    /**
     * Factory method for attaching a existing backend service to a converged http load balancer using only its selfLink.
     * This version is used when cataloging existing http load balancers.
     * @param selfLink a server-defined fully-qualified URL for this resource.
     * @return this
     */
    public ConvergedHttpLoadBalancer withExistingBackendService(String selfLink) {
        backendServices.add(new BackendService(selfLink.replaceAll(".*/", ""), null, null, null, null, null, null, null, selfLink, null));
        return this;
    }

    public class BackendServiceBackend {
        private String name;
        private String description;
        private String balancingMode;
        private Float capacityScaler;
        private String group;
        private Integer maxRate;
        private Float maxRatePerInstance;
        private Float maxUtilization;

        BackendServiceBackend(String name, String description, String balancingMode, Float capacityScaler, String group, Integer maxRate, Float maxRatePerInstance, Float maxUtilization) {
            this.name = name;
            this.description = description;
            this.balancingMode = balancingMode;
            this.capacityScaler = capacityScaler;
            this.group = group;
            this.maxRate = maxRate;
            this.maxRatePerInstance = maxRatePerInstance;
            this.maxUtilization = maxUtilization;
        }

        /**
         * Get converged http loadbalancer backendServiceBackend name
         * @return name of the backend service backend
         */
        public String getName() {
            return name;
        }

        /**
         * Get converged http loadbalancer backend service backend description
         * @return description of the backend service backend
         */
        public String getDescription() {
            return description;
        }

        /**
         * Get converged http loadbalancer backend service backend creation timestamp
         * @return creationTimestamp of the backend service backend
         */
        public String getCreationTimestamp() {
            return creationTimestamp;
        }

        /**
         * Get converged http loadbalancer backend service backend balancing mode
         * @return balancingMode of the backend service backend
         */
        public String getBalancingMode() {
            return balancingMode;
        }

        /**
         * Get converged http loadbalancer backend service backend capacity scalar
         * @return capacityScaler of the backend service backend
         */
        public Float getCapacityScaler() {
            return capacityScaler;
        }

        /**
         * Get converged http loadbalancer backend service backend group
         * @return group of the backend service backend
         */
        public String getGroup() {
            return group;
        }

        /**
         * Get converged http loadbalancer backend service backend max rate
         * @return maxRate of the backend service backend
         */
        public Integer getMaxRate() {
            return maxRate;
        }

        /**
         * Get converged http loadbalancer backend service backend max rate per instance
         * @return maxRatePerInstance of the backend service backend
         */
        public Float getMaxRatePerInstance() {
            return maxRatePerInstance;
        }

        /**
         * Get converged http loadbalancer backend service backend max utilization
         * @return maxUtilization of the backend service backend
         */
        public Float getMaxUtilization() {
            return maxUtilization;
        }
    }

    /**
     * Factory method for attaching backend service backend to a backend of a converged http load balancer.
     * This version is used when cataloging existing http load balancers.
     * @param name the name for the backend service backend
     * @param description the description for the backend service backend
     * @param balancingMode cpu utilization or requests per second
     * @param capacityScaler This percentage reduces the number of new requests that are sent to the backend. Set this value to 0% to prevent new connections.
     * @param group the name of the backend group
     * @param maxRate the target requests per second for the instance group average. When this rate is exceeded, requests are directed to another backend
     * @param maxRatePerInstance he target requests per second for individual instances. When this rate is exceeded, requests are directed to another backend
     * @param maxUtilization when the average utilization of the group exceeds this percentage, traffic is routed to another group
     * @return this
     */
    public ConvergedHttpLoadBalancer withBackendServiceBackend(String name, String description, String balancingMode, Float capacityScaler, String group, Integer maxRate, Float maxRatePerInstance, Float maxUtilization) {
        backendServiceBackends.add(new BackendServiceBackend(name, description, balancingMode, capacityScaler, group, maxRate, maxRatePerInstance, maxUtilization));
        return this;
    }

    public class HealthCheck {
        private String name;
        private String description;
        private String creationTimestamp;
        private String host;
        private Integer port;
        private String requestPath;
        private Integer checkIntervalSec;
        private Integer timeoutSec;
        private Integer healthyThreshold;
        private Integer unhealthyThreshold;
        private String selfLink;

        HealthCheck(String name, String description, String creationTimestamp, String host, Integer port, String requestPath, Integer checkIntervalSec, Integer timeoutSec, Integer healthyThreshold, Integer unhealthyThreshold, String selfLink) {
            this.name = name;
            this.description = description;
            this.creationTimestamp = creationTimestamp;
            this.host = host;
            this.port = port;
            this.requestPath = requestPath;
            this.checkIntervalSec = checkIntervalSec;
            this.timeoutSec = timeoutSec;
            this.healthyThreshold = healthyThreshold;
            this.unhealthyThreshold = unhealthyThreshold;
            this.selfLink = selfLink;
        }

        /**
         * Get converged http loadbalancer health check name
         * @return name of the health check
         */
        public String getName() {
            return name;
        }

        /**
         * Get converged http loadbalancer health check description
         * @return description of the health check
         */
        public String getDescription() {
            return description;
        }

        public String getCreationTimestamp() {
            return creationTimestamp;
        }

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }

        public String getRequestPath() {
            return requestPath;
        }

        public Integer getCheckIntervalSec() {
            return checkIntervalSec;
        }

        public Integer getTimeoutSec() {
            return timeoutSec;
        }

        public Integer getHealthyThreshold() {
            return healthyThreshold;
        }

        public Integer getUnHealthyThreshold() {
            return unhealthyThreshold;
        }

        public String getSelfLink() {
            return selfLink;
        }

        public void setSelfLink(String selfLink) {
            this.selfLink = selfLink;
        }
    }

    /**
     * Factory method for attaching a existing health check to a converged http load balancer.
     * This version is used when cataloging existing http load balancers.
     * @param name the name of the health check
     * @param description the description of the health check
     * @param creationTimestamp the timestamp for when the health check was created
     * @param host the value set in the host http header 
     * @param port the TCP port number and path for the HTTP health check request
     * @param requestPath the path for the health check to poll
     * @param checkIntervalSec send a health check every interval seconds
     * @param timeoutSec how many seconds to wait before considering no response a timeout
     * @param healthyThreshold the number of consecutive successes required to declare a vm instance healthy
     * @param unhealthyThreshold the number of consecutive failures required to declare a vm instance unhealthy
     * @param selfLink a server-defined fully-qualified URL for this resource.
     * @return this
     */
    public ConvergedHttpLoadBalancer withHealthCheck(String name, String description, String creationTimestamp, String host, Integer port, String requestPath, Integer checkIntervalSec, Integer timeoutSec, Integer healthyThreshold, Integer unhealthyThreshold, String selfLink) {
        healthChecks.add(new HealthCheck(name, description, creationTimestamp, host, port, requestPath, checkIntervalSec, timeoutSec, healthyThreshold, unhealthyThreshold, selfLink));
        return this;
    }

    /**
     * Factory method for attaching a new health check to a converged http load balancer.
     * This version is used when creating a new http load balancer.
     * @param name the name for the health check
     * @param description the description for the health check
     * @param host the value set in the host http header 
     * @param port the TCP port number and path for the HTTP health check request
     * @param requestPath the path for the health check to poll
     * @param checkIntervalSec send a health check every interval seconds
     * @param timeoutSec how many seconds to wait before considering no response a timeout
     * @param healthyThreshold the number of consecutive successes required to declare a vm instance healthy
     * @param unhealthyThreshold the number of consecutive failures required to declare a vm instance unhealthy
     * @return this the ConvergedHttpLoadBalancer object
     */
    public ConvergedHttpLoadBalancer withHealthCheck(String name, String description, String host, Integer port, String requestPath, Integer checkIntervalSec, Integer timeoutSec, Integer healthyThreshold, Integer unhealthyThreshold) {
        healthChecks.add(new HealthCheck(name, description, null, host, port, requestPath, checkIntervalSec, timeoutSec, healthyThreshold, unhealthyThreshold, null));
        return this;
    }

    /**
     * Factory method for attaching a existing health check to a converged http load balancer using only its selfLink.
     * This version is used when cataloging existing http load balancers.
     * @param selfLink a server-defined fully-qualified URL for this resource.
     * @return this
     */
    public ConvergedHttpLoadBalancer withExistingHealthCheck(String selfLink) {
        healthChecks.add(new HealthCheck(selfLink.replaceAll(".*/", ""), null, null, null, null, null, null, null, null, null, selfLink));
        return this;
    }

    /**
     * Get converged http load balancer description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get converged http load balancer url map self link
     * @param selfLink the selfLink for the urlMap 
     */
    public void setUrlMapSelfUrl(String selfLink) {
        this.selfLink = selfLink;
    }

    /**
     * Get converged http load balancer url map self link
     * @return selfLink
     */
    public String getSelfLink() {
        return selfLink;
    }

    /**
     * Get converged http load balancer url map creation timestamp
     * @return creationTimestamp
     */
    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Get converged http load balancer url map default backend service
     * @return defaultBackendService
     */
    public String getDefaultBackendService() {
        return defaultBackendService;
    }

    @Override
    public Map<String, String> getTags() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTag(String key, String value) {
        // TODO Auto-generated method stub
    }

    /**
     * Get converged http load balancer name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get converged http load balancer's backend service self link
     * @param name the name of the backend service to get the self link for
     * @return backendServiceSelfLink
     */
    public String getBackendServiceSelfUrl(@Nonnull String name) {
        for (BackendService backendService : backendServices) {
            if (backendService.getName().equals(name)) {
                return backendService.getSelfLink();
            }
        }
        return null;
    }

    /**
     * Get converged http load balancer's target Proxy self link
     * @param name the name of the target proxy to get the self link for
     * @return targetHttpProxySelfLink
     */
    public String getTargetProxySelfUrl(@Nonnull String name) {
        for (TargetHttpProxy targetHttpProxy : targetHttpProxies) {
            if (targetHttpProxy.getName().equals(name)) {
                return targetHttpProxy.getSelfLink();
            }
        }
        return null;
    }

    /**
     * Get converged http load balancer's healthcheck self link
     * @param healthCheckName the name of the health check to get the self link for
     * @return healthCheckSelfLink
     */
    public String getHealthCheckSelfUrl(String healthCheckName) {
        for (HealthCheck healthCheck : healthChecks) {
            if (healthCheck.getName().equals(healthCheckName)) {
                return healthCheck.getSelfLink();
            }
        }
        return null;
    }
}
