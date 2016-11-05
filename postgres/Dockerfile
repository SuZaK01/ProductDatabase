FROM postgres:9.6
MAINTAINER Lukasz <lukaszek_1@tlen.pl>

ENV TINI_VERSION=0.9.0
ENV TINI_URL=https://github.com/krallin/tini/releases/download/v${TINI_VERSION}/tini

RUN apt-get update && \
    apt-get install -y wget curl tar zip unzip bzip2 rsync net-tools hostname telnet jq make git vim python  && \
    apt-get clean && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /tmp/* /var/tmp/*


# Download apps
RUN echo "====> Prepare tini <====" && \
    curl -L $TINI_URL -o "/usr/local/bin/tini" && \
    chmod u+x "/usr/local/bin/tini"

RUN wget https://storage.googleapis.com/kubernetes-release/release/v1.4.0/bin/linux/amd64/kubectl \
 -O "/usr/local/bin/kubectl" &&  chmod u+x "/usr/local/bin/kubectl"

COPY dbinit/* /docker-entrypoint-initdb.d/

EXPOSE 5432