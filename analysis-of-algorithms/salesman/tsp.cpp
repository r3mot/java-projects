#include <iostream>
#include <vector>
#include <list>

#include <limits>

class TSP
{
public:
    struct Route
    {
        std::vector<int> cities;
        int distance;
    };

    Route Travel(std::vector<std::vector<int>> matrix)
    {
        int startCity = 0;
        int n = static_cast<int>(matrix.size());
        int bestDistance = std::numeric_limits<int>::max();
        Route bestRoute;
        bestRoute.cities = {0, 1, 2, 3};
        bestRoute.distance = 0;
        std::list<Route> routes = {};
        std::vector<int> otherCities = GetOtherCities(n);

        DumpRoutes(startCity, bestRoute, routes);

        for (auto route : routes)
        {
            route.distance = GetDistance(startCity, route, matrix);
            if (route.distance < bestDistance)
            {
                bestDistance = route.distance;
                bestRoute = route;
                // bestRoute.cities = route.cities;
                // bestRoute.distance = distance;
            }
        }

        bestRoute.cities.push_back(startCity);
        return bestRoute;
    };

private:
    int GetDistance(int start, Route route, std::vector<std::vector<int>> matrix)
    {
        int distance = 0;
        int prev = start;

        for (int next : route.cities)
        {
            distance += matrix[prev][next];
            prev = next;
        }

        distance += matrix[prev][start];

        return distance;
    };

    void DumpRoutes(int current, Route &route, std::list<Route> &routes)
    {
        int size = 3;
        if (current == size - 1)
        {
            routes.push_back(route);
            return;
        };

        for (int i = current; i < size; i++)
        {
            std::swap(route.cities[current], route.cities[i]);
            DumpRoutes(current + 1, route, routes);
            std::swap(route.cities[current], route.cities[i]);
        }
    };

    std::vector<int> GetOtherCities(int n)
    {
        std::vector<int> other(n - 1);
        for (auto i = 1; i < n; i++)
        {
            other[i - 1] = i;
        }
        return other;
    };
};

int main()
{

    TSP tsp;

    // best route is 0 -> 1 -> 3 -> 2 -> 0
    // best distance is 85
    std::vector<std::vector<int>> matrix1 = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}};

    // best route is 0 -> 2 -> 1 -> 3 -> 0
    // best distance is 74
    std::vector<std::vector<int>> matrix2 = {
        {0, 29, 20, 21},
        {29, 0, 15, 18},
        {20, 15, 0, 25},
        {21, 18, 25, 0}};

    TSP::Route route = tsp.Travel(matrix2);

    std::cout << "Route: ";
    for (int city : route.cities)
    {
        std::cout << city << " ";
    }

    std::cout << std::endl;
    std::cout << "Distance: " << route.distance << std::endl;

    return 0;
}