package com.github.throyer.app.modules.shared.utils;

import static com.github.throyer.app.modules.infra.environments.SecurityEnvironments.HASH_ID;

import java.util.Arrays;

public class HashIdsUtils {
  public static String encode(Long id) {
    return HASH_ID.encode(id);
  }

  public static Long decode(String id) {
    return Arrays.stream(HASH_ID.decode(id))
      .boxed()
        .findFirst()
          .orElse(null);
  }
}