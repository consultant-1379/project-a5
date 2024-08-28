FROM python:3
WORKDIR /data
RUN pip install pydriller && pip install coverage # && pip install pandas
VOLUME /data
COPY . .
ENV COVERAGE_FILE="coverage-reports/.coverage.xml"
# CMD ["coverage run", "-m", "unittest"]
CMD ["python", "-u", "getStats.py"]
