const {defaults} = require('jest-config');

module.exports = {
  verbose: true,

  moduleFileExtensions: [...defaults.moduleFileExtensions, 'ts', 'tsx', 'js', 'jsx', 'json', 'vue'],

  transform: {
    '^.+\\.vue$': 'vue-jest',
    '.+\\.(css|styl|less|sass|scss|svg|png|jpg|ttf|woff|woff2)$':
      'jest-transform-stub',
    "^.+\\.[t|j]sx?$": "babel-jest"
    // '^.+\\.jsx?$': 'babel-jest',
  },

  moduleNameMapper: {
    '^@/(.*)$': '<rootDir>/src/$1',
    "\\.(jpg|jpeg|png|gif|eot|otf|webp|svg|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$":
      "<rootDir>/__mocks__/fileMock.js",
  },

  snapshotSerializers: ['jest-serializer-vue'],

  testMatch: [
    '**/tests/unit/**/**/*.spec.(js|jsx|ts|tsx)|**/__tests__/*.(js|jsx|ts|tsx)'
  ],

  // collectCoverageFrom: ['src/utils/**/*.{js,vue}', '!src/utils/auth.js', '!src/utils/request.js', 'src/components/article/*.{js,vue}','src/components/table/*.{js,vue}'],
  collectCoverageFrom: ['src/components/article/*.{js,vue}','src/components/table/*.{js,vue}'],
  coverageDirectory: '<rootDir>/tests/unit/coverage',

  collectCoverage: true,
  coverageReporters: [
    'lcov',
    'text-summary'
  ],

  setupFiles: ['<rootDir>/.jest/register-context.js'],

  testURL: 'http://localhost/',
  preset: '@vue/cli-plugin-unit-jest'
};
