import { HttpInterceptorFn } from '@angular/common/http';


export const authInterceptorFn: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('authToken'); // Token'ınızı buradan alın

  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(req);
};