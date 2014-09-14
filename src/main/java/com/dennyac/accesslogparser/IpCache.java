package com.dennyac.accesslogparser;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;

/**
 * The IpCache class uses Google's Guava Cache to hold ip details which have been requested. When
 * the details are not present in the cache the details are fetched from the IpMaster which manages
 * a pool of APIs.
 * @author Denny Abraham Cheriyan
 * @version 1.0, Sep 2014
 */
public class IpCache {

  private LoadingCache<String, IpDetails> cache;

  public IpCache() {
    final IpMaster ipMaster = new IpMaster();
    cache =
        CacheBuilder.newBuilder().maximumSize(10000).build(new CacheLoader<String, IpDetails>() {
          public IpDetails load(String ip) throws Exception {
            return ipMaster.getIpDetails(ip);
          }
        });
  }

  public IpCache(long size) {
    final IpMaster ipMaster = new IpMaster();
    cache = CacheBuilder.newBuilder().maximumSize(size).build(new CacheLoader<String, IpDetails>() {
      public IpDetails load(String ip) throws Exception {
        return ipMaster.getIpDetails(ip);
      }
    });
  }

  /**
   * Gets the details of the ip from the cache. If it doesn't exist, it will request the ip details
   * from IpMaster
   * @param The ip for which details are required
   * @return An IpDetails object which contains information about the ip
   * @throws ExecutionException the execution exception
   */
  public IpDetails getIpDetails(String ip) throws ExecutionException {
    return cache.get(ip);
  }
}
