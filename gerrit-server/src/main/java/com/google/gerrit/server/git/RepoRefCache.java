// Copyright (C) 2016 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.server.git;

import com.google.common.base.Optional;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefDatabase;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** {@link RefCache} backed directly by a repository. */
public class RepoRefCache implements RefCache {
  private final RefDatabase refdb;
  private final Map<String, Optional<ObjectId>> ids;

  public RepoRefCache(Repository repo) {
    this.refdb = repo.getRefDatabase();
    this.ids = new HashMap<>();
  }

  @Override
  public Optional<ObjectId> get(String refName) throws IOException {
    Optional<ObjectId> id = ids.get(refName);
    if (id != null) {
      return id;
    }
    Ref ref = refdb.exactRef(refName);
    id = ref != null
        ? Optional.of(ref.getObjectId())
        : Optional.<ObjectId>absent();
    ids.put(refName, id);
    return id;
  }
}
