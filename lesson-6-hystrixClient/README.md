##Ribbon断路器
由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，
此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，
会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应。
###项目基于:lesson-4-discoverClient

