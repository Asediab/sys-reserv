// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  urlEstablishmentApi: 'http://localhost:9091/apiEst/establishment',
  urlCommentApi: 'http://localhost:9091/apiEst/comment',
  urlReservationApi: 'http://localhost:9091/apiRes/reservation',
  urlUserApi: 'http://localhost:9091/uaa/users',
  accessTokenUri: 'http://localhost:9091/uaa/oauth/token',
  tokenInfoUri: 'http://localhost:9091/uaa/oauth/check_token',
  userInfoUri: 'http://localhost:9091/uaa/users/current'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
