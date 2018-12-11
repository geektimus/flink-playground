# Apache Flink Playground

[![version](https://img.shields.io/badge/version-0.0.1-green.svg)][semver]
[![Build Status](https://travis-ci.org/geektimus/flink-playground.svg?branch=master)](https://travis-ci.org/geektimus/flink-playground)

This project will contain all the code I will create while researching flink usages and how it compares with Apache Spark / Apache Beam / Kafka Streams

## Getting Started

These instructions will get you a copy of the app to be used on your local machine.

### Prerequisites

To use, modify or at least run the tests, you need to have SBT installed on your local development environment.
### Installing

- Clone the repository

```
git clone git@github.com:geektimus/flink-playground.git
cd ~/flink-playground
```

## Running the tests

In case you want to run the tests it's as simple as running this command. 

```
sbt test
```

## Usage

TBD

## Built With

- [Scala 2.12.8][scala] - Base language
- [Apache Flink 1.7.0][apache_flink] - Streaming framework

## On going Task

- Create a set of docker containers with all the playground ready to process data and show some graphs
- Keep learning 😄

## Contributing

Please read [CONTRIBUTING.md][contributing] for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer][semver] for versioning. For the versions available, see the [tags on this repository][project_tags].

## Authors

- **Alex Cano** - _Initial work_ - [Geektimus][profile]

See also the list of [contributors][project_contributors] who participated in this project.

## License

[![license](https://img.shields.io/badge/license-MIT-blue.svg)][license]

This project is licensed under the MIT License - see the [LICENSE.md][license] file for details

[travis_url]: https://travis-ci.org/geektimus/file-merger
[scala]: https://github.com/scala/scala/releases/tag/v2.12.8
[apache_flink]: https://flink.apache.org/
[contributing]: CONTRIBUTING.md
[semver]: http://semver.org/
[project_tags]: https://github.com/geektimus/flink-playground/tags
[profile]: https://github.com/Geektimus
[project_contributors]: https://github.com/geektimus/flink-playground/graphs/contributors
[license]: LICENSE.md
