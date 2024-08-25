import { Box, Flex, Heading, Text, Image, Button } from '@chakra-ui/react';
import { Link } from 'react-router-dom';
import mosquitoImage from './assets/hateMosquito.jpg'; // Import your mosquito image

export default function Home() {
    return (
        <Box>
        <Flex
            direction="column"
            align="center"
            justify="center"
            h="100vh"
            bg="gray.100"
        >
            <Image src={mosquitoImage} alt="Mosquito" w="400px" mb={8} />
            <Heading as="h1" size="4xl" mb={4}>
            I Hate Mosquitos.
            </Heading>
            <Text fontSize="xl" mb={8}>
            Welcome to our website, where we share our passion for keeping mosquitos
            at bay.
            </Text>
            <Flex>
            <Box mr={4}>
                <Link to="/services" _hover={{ textDecoration: 'none' }}>
                <Button colorScheme="blue">About</Button>
                </Link>
            </Box>
            <Box>
                <Link to="/services" _hover={{ textDecoration: 'none' }}>
                <Button colorScheme="blue">Services</Button>
                </Link>
            </Box>
            </Flex>
        </Flex>
        </Box>
    );
}
