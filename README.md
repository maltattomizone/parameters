# Parameters

A resolver that can replace parameters like slf4j
* in order
* fast
* fails with non matchined braces
* {} and '' can be used with the latter keeping the quotes post replace

```
ParameterResolver.resolve("User '' created ticket {}", "user id", 12312312)
```
