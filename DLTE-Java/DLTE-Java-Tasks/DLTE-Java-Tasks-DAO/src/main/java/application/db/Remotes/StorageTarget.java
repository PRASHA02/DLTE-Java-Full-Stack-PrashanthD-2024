package application.db.Remotes;

import java.io.IOException;

public interface StorageTarget {
    UserInfoRepository getUserInfoRepository() throws IOException;
}
