## Configuring Server Port At Application.Properties 
```
serevr.port = 8081
```

## Automatic build tool's setup at intellij IDEA

```
1.  go to: file->settings->build,execution,deployment. Go to ->compiler->build project automatically.
2.  SHIFT+Ctrl+A ->registry-> compiler.automake.allow.when.app.running
```

## Global CORS Configuration

```
Update client application baseURL
registry.addMapping("/**").allowedOrigins("http://localhost:****","https://***.app");

using @PropertySource and @Value annotations

```