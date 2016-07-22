Dependency not found in maven repository: webp-imageio (https://bitbucket.org/luciad/webp-imageio/downloads). download the version 0.4.2, rename as "webp-imageio-0.4.2.jar" and run:

mvn install:install-file -Dfile=\<path-to-file\> -DgroupId=webp-imageio -DartifactId=webp-imageio -Dversion=0.4.2 -Dpackaging=jar
    
where \<path-to-file\> must be replaced by the path of the webp-imageio downloaded.

Ensure libwebp-imageio.so, libwebp-imageio.dylib or webp-imageio.dll is accessible on the Java native library path (java.library.path system property) (note from https://bitbucket.org/luciad/webp-imageio/overview)
