import React from 'react';
import { Box, SimpleGrid, Text, Heading, VStack, Flex, Image } from '@chakra-ui/react';
import sunnyImage from './assets/sunny.png'; // Import your mosquito image
import rainyImage from './assets/rainy.png'; // Import your mosquito image
import snowImage from './assets/snow.png'; // Import your mosquito image
import cloudyImage from './assets/cloudy.png'; // Import your mosquito image

const weeklyForecast = [
  { day: 'Mon', temp: '25°C', humidity: '60%', mosquitoIndex: 20.5, description: 'Sunny' },
  { day: 'Tue', temp: '28°C', humidity: '55%', mosquitoIndex: 45.3, description: 'Cloudy' },
  { day: 'Wed', temp: '30°C', humidity: '90%', mosquitoIndex: 78.9, description: 'Rainy' },
  { day: 'Thu', temp: '-15°C', humidity: '85%', mosquitoIndex: 34.7, description: 'Snow' },
  { day: 'Fri', temp: '26°C', humidity: '70%', mosquitoIndex: 59.1, description: 'Sunny' },
  { day: 'Sat', temp: '29°C', humidity: '60%', mosquitoIndex: 88.4, description: 'Cloudy' },
  { day: 'Sun', temp: '31°C', humidity: '95%', mosquitoIndex: 72.3, description: 'Rainy' },
];

const todayWeather = {
  temp: '30°C',
  humidity: '50%',
  mosquitoIndex: 95.7,
  description: 'Sunny',
  windSpeed: '10 km/h',
};

const weatherImages = {
  Sunny: sunnyImage,
  Cloudy: cloudyImage,
  Rainy: rainyImage,
  Snow: snowImage,
};

const getMosquitoEmoji = (index) => {
  if (index < 20) return { emoji: '😌', color: 'green.300' };
  if (index < 40) return { emoji: '😊', color: 'yellow.300' };
  if (index < 60) return { emoji: '😐', color: 'orange.300' };
  if (index < 80) return { emoji: '😟', color: 'red.300' };
  return { emoji: '😖', color: 'red.500' };
};

export default function Graph() {
  const { emoji, color } = getMosquitoEmoji(todayWeather.mosquitoIndex);

  return (
    <Flex align="center" justify="center" minH="100vh" p={4}>
      <VStack spacing={8} w="100%" maxW="1200px">
        <Box w="100%">
          <Heading as="h2" size="lg" mb={4} textAlign="center">Weekly Forecast</Heading>
          <SimpleGrid columns={7} spacing={4}>
            {weeklyForecast.map((day, index) => (
              <Box
                key={index}
                p={4}
                borderWidth="1px"
                borderRadius="lg"
                textAlign="center"
                bg="white"
              >
                <Image
                  src={weatherImages[day.description]}
                  alt={day.description}
                  borderRadius="lg"
                  mb={2}
                />
                <Text fontWeight="bold">{day.day}</Text>
                <Text>Temp: {day.temp}</Text>
                <Text>Humidity: {day.humidity}</Text>
                <Text>Mosquito Index: {day.mosquitoIndex.toFixed(1)}</Text>
              </Box>
            ))}
          </SimpleGrid>
        </Box>

        <Box w="100%">
          <Heading as="h2" size="lg" mb={4} textAlign="center">Today's Weather</Heading>
          <Flex justify="center">
            <Box
              p={8}
              borderWidth="1px"
              borderRadius="lg"
              textAlign="center"
              w="50%"
              bg="white"
            >
              <Flex>
                <Box w="50%" textAlign="center">
                  <Image
                    src={weatherImages[todayWeather.description]}
                    alt={todayWeather.description}
                    borderRadius="lg"
                    mb={4}
                  />
                  <Text fontSize="4xl" fontWeight="bold">{todayWeather.temp}</Text>
                  <Text fontSize="2xl">{todayWeather.humidity}</Text>                 
                  <Text fontSize="2xl">{todayWeather.windSpeed}</Text>

                </Box>
                <Box w="50%" textAlign="center">
                  <Flex direction="column" align="center" justify="center" height="100%">
                    <Text fontSize="8xl" mb={2}>{emoji}</Text>
                    <Text fontSize="2xl" fontWeight="bold" color={color}>
                      Mosquito Index: {todayWeather.mosquitoIndex.toFixed(1)}
                    </Text>
                  </Flex>
                </Box>
              </Flex>
            </Box>
          </Flex>
        </Box>
      </VStack>
    </Flex>
  );
}